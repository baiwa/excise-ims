const allowances = [
    [240, 240, 240, 270],
    [240, 240, 240, 270, 270],
    [240, 270],
    [270, 270]
];
const restSingle = [
    [1500, 1500, 1500, 2200],
    [1500, 1500, 1500, 2200, 2500],
    [1500, 2200],
    [2200, 2500]
];
const restDouble = [
    [850, 850, 850, 1200],
    [850, 850, 850, 1200, 1400],
    [850, 1200],
    [1200, 1400]
];
export var Prices = (typeDoc, topic, what) => {
    switch (what) {
        case 'allowance':
            return allowances[typeDoc][topic];
        case 'rest-single':
            return restSingle[typeDoc][topic];
        case 'rest-double':
            return restDouble[typeDoc][topic];
    }
};

export default { Prices };