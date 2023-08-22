
import {createStore} from 'vuex'
import user from './modules/user.js'
import menu from './modules/menu'

const store = createStore({
	state:{
		hasRoutes: false
	},
	getters:{

	},
	mutations:{
		SET_ROUTES_STATE:(state,hasRoutes)=>{
			state.hasRoutes=hasRoutes
		}
	},
	modules:{
		user,
		menu
	}
})
export default store



