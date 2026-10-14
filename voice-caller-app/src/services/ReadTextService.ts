export const readText = (text: string) => {
    if ('speechSynthesis' in window) {
        const utterance = new SpeechSynthesisUtterance(text);
        utterance.lang = 'pl-PL';
        utterance.rate = 0.7;
        utterance.pitch = 0.5;
        window.speechSynthesis.speak(utterance);
    } else {
        alert('Browser not use speech synthesis');
    }
}