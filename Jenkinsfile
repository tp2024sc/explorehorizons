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
        //stage("test"){
        //    steps{
        //        echo "Start Test"
        //        bat "mvn test"
        //    }
        //}
        stage("sonar"){
            steps{
                echo "Start sonar"
                bat "mvn sonar:sonar -Dsonar.projectKey=ExploreHorizons.jenkinsPipeline -Dsonar.projectName=ExploreHorizons.jenkinsPipeline -Dsonar.sources=src/main -Dsonar.language=java -Dsonar.test=src/test -Dsonar.junit.reportsPath=target/surefire-reports -Dsonar.surefire.reportsPath=target/surefire-reports -Dsonar.jacoco.reportPath=target/jacoco.exec -Dsonar.java.binaries=target/classes -Dsonar.java.coveragePlugin=jacoco"
            }
        }
  
        stage("install"){
            steps{
                echo "Start build"
                bat "mvn install -DskipTests"
            }
        }  
    }
    post {
            always {
                echo 'I will always execute this!'
            }
        }
}