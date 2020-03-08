package org.devops

// Node构建命令

def Build(){
   buildHome = tool "NPM"
   
   // println("${buildHome}")
   
   sh """ 
       ${buildHome}/bin/npm install --registry=https://registry.npm.taobao.org
		 ${buildHome}/bin/npm run build:stage
      """

}
