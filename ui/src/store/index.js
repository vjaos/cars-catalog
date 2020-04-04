import Vue from 'vue'
import Vuex from 'vuex'
import Car from "../models/Car";
import CarService from "../service/CarService";

Vue.use(Vuex)
const carService = new CarService();



export default new Vuex.Store({
    state: {
        cars: [],
        newCar: new Car('', '', '', 1805)
    },
    mutations: {
        DELETE_CAR(state, car) {
            carService.deleteCar(car.id);
            var cars = state.cars
            cars.splice(cars.indexOf(car), 1)
        },
        ADD_CAR(state) {
            carService.createCar(state.newCar).then(data => {
                    state.cars.push(data)
                }
            )
        },
        CLEAR_CAR(state) {
            state.newCar = new Car('', '', '', 1805)
        },
        SET_ITEMS(state, cars) {
            state.cars = cars
        }
    },
    getters: {
        newCar: state => state.newCar,
        cars: state => state.cars
    },
    actions: {
        getCars({commit}) {
            return carService.getCarsList().then(data =>
                commit('SET_ITEMS', data.cars_list)
            )
        },
        addCar({commit}) {
            commit('ADD_CAR')
            commit('CLEAR_CAR')
        },
        removeCar({commit}, car) {
            commit('DELETE_CAR', car)
        }
    },
    modules: {}
})
