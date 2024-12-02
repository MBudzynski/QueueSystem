import {queueSystemFacilityClient} from "../config/QueueSystemFacilityClient"
import {UserData} from "../dto/UserData";

export const loginUser = (userLogin: string, passwords:string) => {

    const UserLoginRequest = {
        userLogin: userLogin,
        password: passwords
    }

    return queueSystemFacilityClient.post<UserData>('/user/login', UserLoginRequest);
}


export const updateUserConfiguration = (userUUID: string, displayServiceDeskName: string,
                                        pronouncedNumberPrefix: string, pronouncedServiceDeskName: string) => {

    const UpdateUserConfigurationRequest = {
        userUUID: userUUID,
        displayServiceDeskName: displayServiceDeskName,
        pronouncedNumberPrefix: pronouncedNumberPrefix,
        pronouncedServiceDeskName: pronouncedServiceDeskName
    }

    return queueSystemFacilityClient.post<UserData>('/user/configuration', UpdateUserConfigurationRequest);
}