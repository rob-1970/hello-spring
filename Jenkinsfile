pipeline {
    agent any

    stages {

        stage('Test') {
            steps {
                sh './gradlew clean test'
            }
            post {
                always {
                    junit 'build/test-results/test/*.xml'
                    jacoco execPattern: 'build/jacoco/*.exec'
                    recordIssues(
                        // Añadimos la lista de herramientas de DIGESTION que tratara Jenkins
                        tools: [
                            // Habilitar herramienta PMD que vamos a tratar desde Jenkins
                            pmdParser(pattern: 'build/reports/pmd/*.xml')
                        ]
                    )
                }
            }
        }

        stage('Build') {
            steps {
                // Get some code from a GitHub repository
                // git 'https://github.com/rob-1970/hello-spring.git'   ====>>> INICIALMENT surt a la rama MASTER
                // Com Jenkins ja fa el control de versions no cal que vaigi a buscar el GitHub
                //git branch: 'main', url: 'https://github.com/rob-1970/hello-spring.git'

                // Run Gradle Wrapper on a Unix agent.
                sh "./gradlew assemble"
            }

            post {
                // If Gradle was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                    archiveArtifacts 'build/libs/*.jar'      // ==>> CONFIGURACIÓ ORIGINAL BÀSICA
                    //archiveArtifacts artifacts: 'build/libs/*.jar', followSymlinks: false
                }
            }
        }

        stage('Deploy') {
            steps{
                echo 'Deploying . . . . . . . . .'
            }
        }

        stage('InfoTEAM')  {
            steps{
                echo 'Info TEAM about SUCCESS . . . . . . . . .'
            }
        }
    }
}