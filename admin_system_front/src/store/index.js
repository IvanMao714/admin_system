
import {createStore} from 'vuex'
import user from './modules/user.js'
import menu from './modules/menu'
import Cookies from "js-cookie";

const store = createStore({
	state:{
		hasRoutes: false
	},
	getters:{

	},
	mutations:{
		SET_ROUTES_STATE:(state,hasRoutes)=>{
			state.hasRoutes=hasRoutes
		},
		setToken:(state,token)=>{
			sessionStorage.setItem('token', token)
		}
	},
	modules:{
		user
	}
})
export default store



