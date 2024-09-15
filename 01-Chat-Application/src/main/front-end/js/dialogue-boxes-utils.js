const popUpOverlay = document.getElementById('popup-overlay');
const popUpDialog = document.getElementById('popup-dialog');

export function openPopup() {
    popUpOverlay.classList.remove('hidden');
    popUpDialog.classList.remove('hidden');
}

export function closePopup() {
    popUpOverlay.classList.add('hidden');
    popUpDialog.classList.add('hidden');
}
