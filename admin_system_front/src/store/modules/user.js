export default {
	state:{
		token:'',
	},
	getters:{},
	mutations:{
		// 初始化用户信息(在App.vue调用)
		initUser(state){
			localStorage.clear()
			let t = localStorage.getItem('token')
			if(t){
				state.token = t
			}
		},
		// 
		login(state,val){
			state.token = val
			localStorage.setItem('token',val)
		},
		
		//退出
		logout(state){
			state.token = ''
			localStorage.clear()
		}
		
	},
	actions:{}
}