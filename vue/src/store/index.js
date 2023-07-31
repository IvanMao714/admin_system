// import Vue from 'vue'
import {createStore} from 'vuex'

// // 引入card
// import card from './modules/card.js'
// // 引入menu.js
// import menu from './modules/menu.js'
//login.js
import user from './modules/user.js'

const store = createStore({
	modules:{
		user
	}
})
export default store