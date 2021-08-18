import { store } from 'react-notifications-component';

class SimpleWriteApi {

    notify(note, notetype) {
        store.addNotification({
            message: note,
            type: notetype,
            insert: "top",
            container: "top-right",
            animationIn: ["animate__animated", "animate__fadeIn"],
            animationOut: ["animate__animated", "animate__fadeOut"],
            dismiss: {
                duration: 3000,
                onScreen: true
            }
        })
    }

    doFetch(url, method, item, entityType) {
        fetch(url, {
            method: method,
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(item),
        }).then((response) => {
            if(response.ok) {
                this.notify(`${entityType} successfully saved.`,"success");
            }else{
                throw new Error((response.statusText || '') + `: ${entityType} could not be saved.`);                
            }
         }).catch((error) => {
            this.notify(error.message,"danger");
            console.log('There has been a problem with your fetch operation: ', error.message);
         });
    }
}

export default new SimpleWriteApi();