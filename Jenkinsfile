pipeline {

    agent any

   tools {
      // Install the Maven version configured as "MAVEN_PADRAO" and add it to the path.
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
    }
}