import {AXIOS} from "../http-common";

class CarService {
    getCarsList() {
        return AXIOS.get("cars/");
    }

    deleteCar(id) {
        return AXIOS.delete("cars/${id}")
    }

    createCar(car) {
        return AXIOS.post("cars/", {
            number: car.number,
            brand: car.brand,
            color: car.color,
            release_year: car.releaseYear
        })
    }
}