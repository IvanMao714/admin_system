import axios from "axios";
import global from "../../global.js";

export default {
    state:{
        menuList:'',
    },
    getters:{


    },
    mutations:{
        SET_MENU_LIST:(state,menuList)=>{
            //state.menuList=menuList;
            localStorage.setItem("menuList", JSON.stringify(menuList))
        },
        getMenuListById:(state, pram) => {
            return axios.get(global.HOST_URL + "/menu/list", {params: pram}).then(
                res => {
                    res = res.data;
                }
            );
        }

    },
    actions:{}
}