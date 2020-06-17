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
                timeout (time: 1, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
    }
}