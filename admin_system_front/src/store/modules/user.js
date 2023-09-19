export default {
	state:{
		token:'',
	},
	getters:{
		getUserDetailInfo(state){
			return JSON.parse(sessionStorage.getItem('userDetail'))
		}
	},
	mutations:{
		
		//退出
		logout(state){
			state.token = ''
			localStorage.clear()
		}
		
	},
	actions:{}
}