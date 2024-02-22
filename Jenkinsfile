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
        stage("sonar"){
            steps{
                echo "Start sonar"
                bat "mvn sonar:sonar"
            }
        }
        stage("test"){
            steps{
                echo "Start Test"
                bat "mvn test"
            }
        }
        stage("install"){
            steps{
                echo "Start build"
                bat "mvn install"
            }
        }
    }
}