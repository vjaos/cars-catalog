<template>
    <v-data-table
            :headers="headers"
            :items="cars"
            :items-per-page=10
            :single-select="true"
    >
        <template v-slot:item.color="{item}">
            <v-chip small :color="getColor(item.color)"/>
        </template>
        <template v-slot:top>
            <v-toolbar color="white" flat>
                <v-spacer></v-spacer>
                <v-dialog max-width="500px" v-model="dialog">
                    <template v-slot:activator="{ on }">
                        <v-btn rounded class="mb-2" color="#7E57C2" dark v-on="on">New Item</v-btn>
                    </template>
                    <v-card>
                        <v-card-title>
                            <span class="headline">Add Car</span>
                        </v-card-title>

                        <v-card-text>
                            <v-container>
                                <div v-if="messages.length">
                                    <p class="error--text" v-for="message in messages"
                                       :key="message"
                                    >
                                        {{ message }}
                                    </p>
                                </div>
                                <v-row>
                                    <v-col cols="12" md="4" sm="6">
                                        <v-text-field outlined label="Number" v-model="newCar.number"></v-text-field>
                                    </v-col>
                                    <v-col cols="12" md="4" sm="6">
                                        <v-select
                                                outlined
                                                v-model="newCar.brand"
                                                label="Brand"
                                                :items="brands"
                                        >
                                        </v-select>
                                    </v-col>
                                    <v-col cols="12" md="4" sm="6">
                                        <v-select outlined
                                                  label="Color"
                                                  v-model="newCar.color"
                                                  :items="colors"
                                        >

                                        </v-select>
                                    </v-col>
                                    <v-col cols="12" md="4" sm="6">
                                        <v-text-field outlined label="Release Year"
                                                      v-model="newCar.releaseYear"></v-text-field>
                                    </v-col>
                                </v-row>
                            </v-container>
                        </v-card-text>
                        <v-card-actions>
                            <v-spacer></v-spacer>
                            <v-btn @click="close" color="#7E57C2" text>Cancel</v-btn>
                            <v-btn @click="add" color="#7E57C2" text>Save</v-btn>
                        </v-card-actions>
                    </v-card>
                </v-dialog>
            </v-toolbar>
        </template>
        <template v-slot:item.actions="{ item }">
            <v-icon
                    color="#F44336"
                    @click="remove(item)"
                    small
            >
                mdi-delete
            </v-icon>
        </template>
    </v-data-table>
</template>

<script>

    export default {
        name: "CarsTable",
        data: () => ({
            dialog: false,
            messages: [],
            headers: [
                {text: 'Id', value: 'id'},
                {text: 'Number', value: 'car_number'},
                {text: 'Brand', value: 'brand'},
                {text: 'Color', value: 'color'},
                {text: 'Release Year', value: 'release_year'},
                {text: 'Actions', value: 'actions'}
            ],
            brands: [
                'Toyota',
                'Audi',
                'BMW',
                'Nissan',
                'Chevrolet',
                'Lada',
                'Lexus'
            ],
            colors: [
                'Red',
                'Black',
                'Purple',
                'Yellow',
                'Green'
            ]
        }),
        created() {
            this.$store.dispatch('getCars')
        },
        computed: {
            newCar() {
                return this.$store.getters.newCar
            },
            cars() {
                return this.$store.getters.cars
            }
        },
        methods: {
            add() {
                this.$store.dispatch('addCar')
                    .then(
                        () => {
                            this.close()
                            this.messages = []
                        },
                        error => {
                            this.messages = []
                            let errorData = error.response.data
                            if (errorData.errors) {
                                for (var i = 0; i < errorData.errors.length; i++) {
                                    this.messages.push(errorData.errors[i].defaultMessage)
                                }
                            } else {
                                this.messages.push(error.response.data.message)
                            }
                        }
                    )
            },
            remove(car) {
                this.$store.dispatch('removeCar', car)
            },
            getCars() {
                this.$store.dispatch('getCars')
            },
            close() {
                this.dialog = false
            },
            getColor(color) {
                var colorCode = "#FFFFFF";
                switch (color) {
                    case 'Red':
                        colorCode = "#F44336";
                        break;
                    case 'Black':
                        colorCode = "#212121";
                        break;
                    case 'Purple':
                        colorCode = "#7E57C2";
                        break;
                    case 'Yellow':
                        colorCode = "#FFB300";
                        break;
                    case 'Green':
                        colorCode = "#43A047";
                        break;
                    default:
                        colorCode = "#F44336";
                }
                return colorCode;
            }
        }
    }
</script>

<style scoped>
    .border {
        border-color: grey;
    }
</style>