pipeline {
    agent any
    stages {
        stage('Clean & Build Project') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Run Unit Tests') {
            steps {
                sh 'mvn test'
            }
        }
        stage('SonarQube Scanner') {
            steps {
                sh 'mvn sonar:sonar'
            }
        }
        stage('Deploy on Nexus') {
            steps {
                sh 'mvn deploy'
            }
        }
    }
}
