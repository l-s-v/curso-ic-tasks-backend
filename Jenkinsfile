pipeline {

    agent any

   tools {
      // Install the Maven version configured as "MAVEN_PADRAO" and add it to the path.
      maven 'MAVEN_PADRAO'
   }

    stages {

        stage ('Build Backend') {
            steps {
                sh 'mvn -DskipTests=true clean package'
            }
        }
    }
}