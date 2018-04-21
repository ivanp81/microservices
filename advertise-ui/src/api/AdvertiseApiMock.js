import delay from './delay';

// This file mocks a web API by working with the hard-coded data below.
// It uses setTimeout to simulate the delay of an AJAX call.
// All calls return promises.
const advertises = [
    {
        id: "1",
        title: "Java developer",
        content: "Lorem ipsum"
    },
    {
        id: "2",
        title: "C++ developer",
        content: "Lorem ipsum"
    },
];

function replaceAll(str, find, replace) {
    return str.replace(new RegExp(find, 'g'), replace);
}

//This would be performed on the server in a real app. Just stubbing in.
const generateId = (advertise) => {
    return replaceAll(advertise.title, ' ', '-');
};

class AdvertiseApi {
    static getAllAdvertises() {
        return new Promise((resolve) => {
            setTimeout(() => {
                resolve(Object.assign([], advertises));
            }, delay);
        });
    }

    static saveAdvertise(advertise) {
        advertise = Object.assign({}, advertise); // to avoid manipulating object passed in.
        return new Promise((resolve, reject) => {
            setTimeout(() => {
                // Simulate server-side validation
                const minAdvertiseTitleLength = 1;
                if (advertise.title.length < minAdvertiseTitleLength) {
                    reject(`Title must be at least ${minAdvertiseTitleLength} characters.`);
                }

                if (advertise.id) {
                    const existingAdvertiseIndex = advertises.findIndex(a => a.id === advertise.id);
                    advertises.splice(existingAdvertiseIndex, 1, advertise);
                } else {
                    //Just simulating creation here.
                    //The server would generate ids and watchHref's for new advertises in a real app.
                    //Cloning so copy returned is passed by value rather than by reference.
                    advertise.id = generateId(advertise);
                    advertise.watchHref = `http://www.pluralsight.com/advertises/${advertise.id}`;
                    advertises.push(advertise);
                }

                resolve(advertise);
            }, delay);
        });
    }

    static deleteAdvertise(advertiseId) {
        return new Promise((resolve) => {
            setTimeout(() => {
                const indexOfAdvertiseToDelete = advertises.findIndex(advertise => advertise.id === advertiseId);
                advertises.splice(indexOfAdvertiseToDelete, 1);
                resolve();
            }, delay);
        });
    }


    static getAdvertise(advertiseId) {
        return new Promise((resolve) => {
            setTimeout(() => {
                const existingAdvertiseIndex = advertises.findIndex(advertise => advertise.id === advertiseId);

                const advertiseFound = Object.assign({}, advertises[existingAdvertiseIndex]);

                resolve(advertiseFound);

            }, delay);
        });
    }

}

export default AdvertiseApi;
