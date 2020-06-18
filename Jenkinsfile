pipeline {

    agent any

   tools {
      // Instale e adicione variáveis ao path
      maven 'MAVEN_PADRAO'
   }

    stages {

        stage ('Build Backend') {
            steps {
                sh 'mvn -gs /var/jenkins_home/extras/mvn_settings.xml -DskipTests=true clean package'
            }
        }

        stage ('Unit Tests') {
            steps {
                sh 'mvn -gs /var/jenkins_home/extras/mvn_settings.xml test'
            }
        } 

        stage ('Sonar Analysis') {
            environment {
                scannerHome = tool 'SONAR_SCANNER'
            }
            steps {
                withSonarQubeEnv('SONAR_PADRAO') {
                    sh '${scannerHome}/bin/sonar-scanner -e -Dsonar.host.url=http://sonar.curso-ic/ -Dsonar.login=c7bc9ba1fdf19d5f8fbe83fb00043c695b449c51 -Dsonar.projectKey=DeployBack -Dsonar.java.binaries=target -Dsonar.coverage.exclusions=**/src/test/**,**/model/**,**Application.java'
                }
            }
        }

        stage("Quality Gate"){
            steps {
                sleep(10);

                timeout (time: 1, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }

        stage("Deploy Backend"){
            steps{
                deploy adapters: [tomcat8(credentialsId: 'TomcatLogin', path: '', url: 'http://tomcat.curso-ic/')], contextPath: 'tasks-backend', war: 'target/tasks-backend.war'
            }
        }

        stage("Deploy Frontend"){            
            steps{
                dir ('frotend') {
                    git credentialsId: 'github_login', url: 'https://github.com/lelandro/tasks-frontend.git'
                    sh 'mvn -gs /var/jenkins_home/extras/mvn_settings.xml clean package'
                    deploy adapters: [tomcat8(credentialsId: 'TomcatLogin', path: '', url: 'http://tomcat.curso-ic/')], contextPath: 'tasks', war: 'target/tasks.war'
                }                
            }
        }
    }
    post {            
        always {
            junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'
        }
    }    
}