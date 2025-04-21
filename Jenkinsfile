/* Requires the Docker Pipeline plugin */
pipeline {
    agent { docker { image 'maven:3.9.9-eclipse-temurin-21-alpine' } }
    stages {
        stage('build') {
            steps {
                sh 'echo ***** COMPILANDO PROYECTO *****'
                sh 'lein compile :all'
                sh 'echo ***** FIN COMPILACIÓN *****'
                sh 'echo ***** COMIENZO ANÁLISIS ESTÁTICO *****'
                sh 'clj-kondo --lint .'
                sh 'echo ***** FIN ANÁLISIS ESTÁTICO *****'
            }
        }
    }
}