pipeline {
    agent any
    stages {
        stage('Plan') {
            steps {
                sh 'terraform init'
                sh 'terraform plan -out=tfplan'
            }
        }
        stage('Test') {
            steps {
                sh 'terraform validate'
            }
        }
        stage('Apply') {
            steps {
                sh 'terraform apply -auto-approve tfplan'
            }
        }
    }
}