package org.devops

def SonarScan(){
    def projectName=env.JOB_NAME
    def projectDesc="测试项目"
    def projectPath=env.WORKSPACE
    
    withSonarQubeEnv(credentialsId: 'SonaerServer-Admin') {
      def scannerHome = "/usr/local/sonar-scanner"
      def sonarDate = sh returnStdout: true, script: 'date +%Y%m%d%H%M%S'
      sonarDate = sonarDate - "\n"
      
      
      sh """
        ${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=${projectName} \
        -Dsonar.projectName=${projectName} \
        -Dsonar.projectVersion=${sonarDate} \
        -Dsonar.ws.timeout=30 \
        -Dsonar.projectDescription=${projectDesc} \
        -Dsonar.language=php \
        -Dsonar.dynamicAnalysis=false \
        -Dsonar.sourceEncoding=UTF-8
      """
  }
}
