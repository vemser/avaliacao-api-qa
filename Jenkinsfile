pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                script {
                    checkout scm
                }
            }
        }
        stage('Test') {
            steps {
                script {
                    bat 'mvn -e clean test -DSkipTests=true'
                }
            }
        }
        stage('Publish Allure Report') {
            steps {
                script {
                    bat 'allure generate allure-results -o allure-report'

                    archiveArtifacts 'allure-report/**'
                }
            }
        }
    }

    post {
        always {
            allure(
                includeProperties: false,
                jdk: '',
                results: [[path: 'allure-results']]
            )
            script {
                def buildUrl = env.BUILD_URL
                def buildResult = currentBuild.currentResult
                def branchName = env.BRANCH_NAME
                def buildNumber = env.BUILD_NUMBER


                def printAllure = bat(script: "cd C:\\Users\\Bruno Artêmio\\Desktop\\DBC\\PF-Avaliação\\jenkins-config\\APIConfig && node capture.js ${env.BUILD_NUMBER}", returnStdout: true).trim()
                def link = "abc"
                try {
                    def matcher = (printAllure =~ /https?:\/\/[^\s]+/)
                    link = matcher.find() ? matcher.group() : "Link não encontrado"
                } catch (Exception e) {
                     echo "Erro ao extrair o link da saída do comando: ${e.message}"
                }
                def ngrok = "https://rodent-dynamic-dane.ngrok-free.app/VemSerAPITestsPipeline/${env.BUILD_NUMBER}/allure/"

                def message = "# Relatorio de Testes/API Validação\n"
                message += "**Branch:** RELEASE\n"
                message += "**Build:** ${buildNumber}\n"
                message += "**Status:** ${buildResult}\n"


                discordSend description: message,
                       link: ngrok
                       image: "${link}",
                       webhookURL: "https://discord.com/api/webhooks/1212783876636811285/C6C-5BDOyXJ5dO6-tQLL_q445JjP9nhuGevK7MJxt8cux2HDjt2prC-2X7McHQe9jQeG"
            }
        }
    }
}