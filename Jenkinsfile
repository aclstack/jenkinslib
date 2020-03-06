@Library('jenkinslib') _  // Jenkins中配置的Library名称
def tools = new org.devops.tools()  // 库中的目录结构,即src下面的东西

String workspace = "/opt/jenkins/workspace"

pipeline{
	// 基础配置
	agent {  node  {  label "build01"		// 指定运行时的标签或名称
							customWorkspace  "${workspace}"		// 指定运行时工作目录(可选) 
		}
	}
	
	options {
			timestamps()	// 日志会有时间
			skipDefaultCheckout()		// 删除隐式checkout scm语句
			disableConcurrentBuilds()	// 禁止并行
			timeout(time:1, unit: "HOURS") // 流水线超时设置1h
	}
	
	// 流水线的阶段
	stages {
		// 下载代码
		stage("GetCode"){ 	// 阶段名称
			steps{	// 步骤
				timeout(time:5, unit: "MINUTES"){
						script {	// 填写运行代码
							println("获取代码")
					 }
					 checkout([$class: 'GitSCM', branches: [[name: '*/master']],
   						doGenerateSubmoduleConfigurations: false,
   						extensions: [], submoduleCfg: [],
   						userRemoteConfigs: [[url: 'https://github.com/PanJiaChen/vue-element-admin.git']]])
				 }
			 }	
		 }
		 
		// 构建
		stage("Build"){
			steps{ 
				timeout(time:20, unit:"MINUTES"){
						script {
							npmHome = tool "NPM"
							sh "${npmHome}/npm install --registry=https://registry.npm.taobao.org && ${npmHome}/npm run build"
						}
					}	
			 }	
		 }
		 
		// 代码扫描
		stage("CodeScan"){ 
			steps{ 	
				timeout(time:5, unit:"MINUTES"){
						script {	
							println("代码扫描")
							tools.PrintMes('获取代码','green')
							tools.PrintMes("超级瞄准已部署", "green")	// share library调用
						}
					}	
				}
		 }
	}
	
	// 构建后操作
	post {
		// 总是执行此处代码
		always {
			script{
				println("always")
			}
		}
		
		// 构建成功后执行此处代码
		success {
			script{
				currentBuild.description = "\n 构建成功!"
				// currentBuild是一个全局变量，而description则是构建描述
			}
		}
	
		// 构建失败后执行此处代码
		failure{
			script{
				currentBuild.description = "\n 构建失败!"
				sh "id"
			}
		}
		
		// 构建取消后执行此处代码
		aborted {
			script{
				currentBuild.description = "\n 构建取消!"
			}
		}
	}
}	
