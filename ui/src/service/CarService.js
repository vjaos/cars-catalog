import {AXIOS} from "../http-common";

export default class CarService {
    getCarsList() {
        return AXIOS.get("/cars").then(response => response.data)
    }

    deleteCar(id) {
        return AXIOS.delete("/cars/" + id)
    }

    createCar(car) {
        return AXIOS.post("/cars", {
            car_number: car.number,
            brand: car.brand,
            color: car.color,
            release_year: car.releaseYear
        }).then(response => response.data)
    }

    getDBStatistic() {
        return AXIOS.get("/cars/statistics").then(response => response.data)
    }
}