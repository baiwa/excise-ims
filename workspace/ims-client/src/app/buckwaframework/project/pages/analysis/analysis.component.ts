import { Component, OnInit } from '@angular/core';

declare var $: any;
@Component({
    selector: 'analysis',
    templateUrl: 'analysis.component.html'
})
export class AnalysisPage implements OnInit {

    private showSelectCoordinate: boolean = false;
    private coordinateList: any[];

    private productList: any[];
    private serviceList: any[];

    constructor(
    ) {

    }

    ngOnInit(): void {
        this.productList = [
            { "value": "สินค้าน้ำมันและผลิตภัณฑ์น้ำมัน" },
            { "value": "สินค้าเครื่องดื่ม" },
            { "value": "สินค้าเครื่องไฟฟ้า" },
            { "value": "สินค้าแบตเตอี่" },
            { "value": "สินค้าแก้วและเครื่องแก้ว" },
            { "value": "สินค้ารถยนต์" },
            { "value": "สินค้ารถจักรยานยนต์" },
            { "value": "สินค้าเรือ" },
            { "value": "สินค้าผลิตภัณฑ์เครื่องหอมและเครื่องสำอาง" },
            { "value": "สินค้าพรมและสิ่งทอปูพื้นอื่นๆ" },
            { "value": "สินค้าหินอ่อนและหินแกรนิต" },
            { "value": "สินค้าสารทำลายชั้นบรรยากาศ" },
            { "value": "สินค้าไพ่" }
        ];

        this.serviceList = [
            { "value": "กิจการบันเทิงหรือหย่อนใจ" },
            { "value": "กิจการเสี่ยงโชค" },
            { "value": "กิจการที่มีผลกระทต่อสิ่งแวดล้อม" },
            { "value": "กิจการที่ได้รับอนุญาตหรือสัมปทานจากรัฐ" },
        ];
    }

    selectCatagory() {
        var value = $('#selectCatagory').val();
        if (value == 1) {
            this.showSelectCoordinate = true;
            this.coordinateList = this.productList;
        } else if (value == 2) {
            this.showSelectCoordinate = true;
            this.coordinateList = this.serviceList;
        } else {
            this.showSelectCoordinate = false;
        }
    }

}