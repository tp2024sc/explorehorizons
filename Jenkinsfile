pipeline{
    agent any
    tools {
        maven 'Maven 395'
        jdk 'OpenJK11'
    }
    stages{
        stage("clean"){
            steps{
                echo "Start Clean"
                bat "mvn clean"
            }
        }
        

        stage('build && SonarQube analysis') {
            steps {
                withSonarQubeEnv('localQube') {
                    // Optionally use a Maven environment you've configured already
                        bat "mvn clean verify sonar:sonar -Dsonar.projectKey=jenkinsPipeline -Dsonar.projectName=ExploreHorizons2 -Dsonar.test=src/test -Dsonar.junit.reportsPath=target/surefire-reports -Dsonar.surefire.reportsPath=target/surefire-reports -Dsonar.jacoco.reportPath=target/jacoco.exec -Dsonar.java.binaries=target/classes -Dsonar.java.coveragePlugin=jacoco"

                }
            }
        }
        stage("install"){
            steps{
                echo "Start build"
                bat "mvn install -DskipTests"
            }
        }
        stage("deploy") {
            steps {
               deploy adapters: [tomcat9 (url: 'http://localhost:8090', credentialsId: 'f3f1ddc1-3e2d-4f23-8766-e3381ac9e2e0')],
                                war:'**/*.war', contextPath: 'ExploreHorizons'
            }
        }
        
    }
    post {
            always {
                echo 'Building Selenium job to test deployed app!'
                build job: "selenium-horizons", wait: true
            }
        }
}