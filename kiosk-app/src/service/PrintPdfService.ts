export const handlePrint = async (printData:string) => {

    try {
        const response = await fetch('http://localhost:3001/print', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ printData }),
        });

        if (!response.ok) {
            throw new Error('Błąd przy drukowaniu');
        }

        const result = await response.json();
        console.log(result);
    } catch (error) {
        console.error('Błąd: ', error);
    }
};