import Vue from 'vue'
import Vuex from 'vuex'
import Car from "../models/Car";
import CarService from "../service/CarService";
import DbStat from "../models/DbStat";

Vue.use(Vuex)
const carService = new CarService();


export default new Vuex.Store({
    state: {
        cars: [],
        newCar: new Car('', '', '', 1805),
        dbStat: new DbStat('', '', '', '', '')
    },
    mutations: {
        DELETE_CAR(state, car) {
            carService.deleteCar(car.id);
            var cars = state.cars
            cars.splice(cars.indexOf(car), 1)
        },
        ADD_CAR(state, data) {
            state.cars.push(data)
        }
        ,
        CLEAR_CAR(state) {
            state.newCar = new Car('', '', '', 1805)
        }
        ,
        SET_ITEMS(state, cars) {
            state.cars = cars
        },
        SET_DB_STAT(state, dbStat) {
            let stat = new DbStat()

            stat.totalCount = dbStat.total_count
            stat.firstCreated = dbStat.first_created_date
            stat.lastCreated = dbStat.last_created_date
            stat.amountOfPurple = dbStat.amount_of_purple_cars
            stat.amountOfRed = dbStat.amount_of_red_cars

            state.dbStat = stat
        }
    },
    getters: {
        newCar: state => state.newCar,
        cars: state => state.cars,
        dbStat: state => state.dbStat
    },
    actions: {
        getCars({commit}) {
            return carService.getCarsList().then(data =>
                commit('SET_ITEMS', data.cars_list)
            )
        },
        addCar({commit}) {
            return carService.createCar(this.state.newCar)
                .then(
                    car => {
                        commit('ADD_CAR', car)
                        commit('CLEAR_CAR')
                    },
                    error => {
                        return Promise.reject(error)
                    }
                )
        },
        removeCar({commit}, car) {
            commit('DELETE_CAR', car)
        },
        getStat({commit}) {
            return carService.getDBStatistic()
                .then(data => {
                    commit('SET_DB_STAT', data)
                })
        }
    },
    modules: {}
})
