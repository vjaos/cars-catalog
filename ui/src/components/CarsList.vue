<template>
    <v-container>
        <v-layout
                justify-center
                align-center
        >
            <v-flex
                    text-center
            >
                <v-card class="elevation-4" width="50%">

                </v-card>
            </v-flex>
        </v-layout>
    </v-container>
</template>

<script>
    export default {
        name: "CarsList",
        data() {
            return {
                carsList: [
                    {
                        id: 1,
                        number: "a123bc",
                        color: "black",
                        brand: "ferrari",
                        releaseYear: 2019
                    },
                    {
                        id: 2,
                        number: "b421sd",
                        color: "white",
                        brand: "Hyndai",
                        releaseYear: "2009"
                    },
                    {
                        id: 3,
                        number: "b421sd",
                        color: "white",
                        brand: "Hyndai",
                        releaseYear: "2009"
                    }, {
                        id: 4,
                        number: "b421sd",
                        color: "white",
                        brand: "Hyndai",
                        releaseYear: "2009"
                    }, {
                        id: 5,
                        number: "b421sd",
                        color: "white",
                        brand: "Hyndai",
                        releaseYear: "2009"
                    }
                ],
                fields: [
                    "number",
                    "color",
                    "brand",
                    "release year"
                ]
            }
        }
    }
</script>

<style scoped>

</style>