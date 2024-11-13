export const saveAtLocalStorage = (key: string, value:string) => {
    localStorage.setItem(key, value);
};

export const getFromLocalStorage = (key: string): string => {
    const text = localStorage.getItem(key);
    if (text) {
        return text;
    }
    return "";
};