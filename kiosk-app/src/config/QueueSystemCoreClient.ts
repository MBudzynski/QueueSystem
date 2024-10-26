import axios from "axios";

const BASE_URL = "http://localhost:8085";

export const queueSystemCoreClient = axios.create({
    baseURL: BASE_URL,
    headers: {
        "Content-Type": "application/json",
    },
});