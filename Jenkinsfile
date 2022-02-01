pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                // Get some code from a GitHub repository
                // git 'https://github.com/rob-1970/hello-spring.git'   ====>>> INICIALMENT surt a la rama MASTER
                // Com Jenkins ja fa el control de versions no cal que vaigi a buscar el GitHub
                //git branch: 'main', url: 'https://github.com/rob-1970/hello-spring.git'

                // Run Gradle Wrapper on a Unix agent.
                sh "./gradlew clean test assemble"
            }

            post {
                // If Gradle was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                    junit 'build/test-results/test/*.xml'
                    archiveArtifacts 'build/libs/*.jar'      // ==>> CONFIGURACIÓ ORIGINAL BÀSICA
                    //archiveArtifacts artifacts: 'build/libs/*.jar', followSymlinks: false
                    jacoco execPattern: 'build/jacoco/*.exec'

                }
            }
        }
    }
}