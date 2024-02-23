pipeline{
    agent any
    environment { 
      TOMCAT_CREDENTIALS = credentials('tomcat')
    }
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
        
        //stage("compile"){
        //    steps{
        //        echo "Start compile"
        //        bat "mvn compile"
        //    }
        //}
        //stage("test"){
        //    steps{
        //        echo "Start Test"
        //        bat "mvn test"
        //    }
        //}
        //stage("sonar"){
        //    steps{
        //        echo "Start sonar"
        //        bat "mvn sonar:sonar -Dsonar.projectKey=jenkinsPipeline -Dsonar.projectName=ExploreHorizons2"
        //    }
        //}
        
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
               deploy adapters: [tomcat9(url: 'http://localhost:8090', credentialsId: 'tomcat' )],
                                war:'**/*.war', contextPath: 'ExploreHorizons'
            }
        }
        
    }
    post {
            always {
                echo 'I will always execute this!'
                
            }
        }
}