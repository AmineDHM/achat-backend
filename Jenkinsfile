pipeline {
    agent any
    stages {
        stage('Clean & Build Project') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('SonarQube Scanner') {
            steps {
                sh 'mvn sonar:sonar'
            }
        }
    }
}
