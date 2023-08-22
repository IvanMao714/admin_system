export default {
    state:{
        menuList:'',
    },
    getters:{
        GET_MENU_LIST:state => {
            return JSON.parse(localStorage.getItem("menuList"))
        },
    },
    mutations:{
        SET_MENU_LIST:(state,menuList)=>{
            //state.menuList=menuList;
            localStorage.setItem("menuList", JSON.stringify(menuList))
        },

    },
    actions:{}
}