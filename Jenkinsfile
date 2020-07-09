pipeline {

    agent any

   tools {
      // Instale e adicione variáveis ao path
      maven 'MAVEN_PADRAO'
   }

    stages {

        stage ('Build Backend') {
            steps {
                sh 'mvn -DskipTests=true clean package'
            }
        }

        stage ('Unit Tests') {
            steps {
                sh 'mvn test'
            }
        } 
    }
    post {            
        always {
            junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'
            archiveArtifacts artifacts: 'target/tasks-backend.war, frontend/target/tasks.war', onlyIfSuccessful: true
        }
    }    
}