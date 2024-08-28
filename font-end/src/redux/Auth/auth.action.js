import axios from "axios"
import { api, API_BASE_URL } from "../../config/api"
import { GET_PROFILE_FAILURE, GET_PROFILE_REQUEST, GET_PROFILE_SUCCESS, LOGIN_FAILURE, LOGIN_REQUEST, LOGIN_SUCCESS, UPDATE_PROFILE_FAILURE, UPDATE_PROFILE_REQUEST, UPDATE_PROFILE_SUCCESS } from "./auth.action.type";

export const loginUserAction = (loginData) => async (dispatch) => {
    dispatch({ type: LOGIN_REQUEST });
    try {

        const { data } = await axios.post(`${API_BASE_URL}/auth/signin`, loginData.data);


        if (data.token) {
            localStorage.setItem("jwt", data.token);
        }
        console.log("check login data =>>", data)
        dispatch({ type: LOGIN_SUCCESS, payload: data.token })
    } catch (error) {
        console.log("check error --->>>", error);
        dispatch({ type: LOGIN_FAILURE, payload: error })
    }
}

export const registerUserAction = (loginData) => async (dispatch) => {
    dispatch({ type: LOGIN_REQUEST });
    try {
        const { data } = await api.post(`${API_BASE_URL}/auth/signup`, loginData.data);

        if (data.token) {
            localStorage.setItem("jwt", data.token);

        }

        dispatch({ type: LOGIN_SUCCESS, payload: data.token })
    } catch (error) {
        console.log("check error --->>>", error);
        dispatch({ type: LOGIN_FAILURE, payload: error })
    }
}

export const getProfileAction = (jwt) => async (dispatch) => {
    dispatch({ type: GET_PROFILE_REQUEST });
    try {
        const { data } = await axios.get(`${API_BASE_URL}/api/users/profile`,
            {
                headers: {
                    "Authorization": `Bearer ${jwt}`,
                },

            }
        );
        console.log("get profile =>>", data)
        dispatch({ type: GET_PROFILE_SUCCESS, payload: data })
    } catch (error) {
        console.log("check error --->>>", error);
        dispatch({ type: GET_PROFILE_FAILURE, payload: error })
    }
}

export const updateProfileAction = (reqData) => async (dispatch) => {
    dispatch({ type: UPDATE_PROFILE_REQUEST });
    try {
        const { data } = await api.put(`${API_BASE_URL}/api/users`,
            reqData);
        console.log("check data update =>>", data)

        dispatch({ type: UPDATE_PROFILE_SUCCESS, payload: data })
    } catch (error) {
        console.log("check error --->>>", error);
        dispatch({ type: UPDATE_PROFILE_FAILURE, payload: error })
    }
}

