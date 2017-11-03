import { Component, OnInit } from '@angular/core';

import { MessageBarService } from './../../../common/services/message-bar.service';

declare var $: any;
@Component({
  selector: 'app-mapping-gf-inc-code',
  templateUrl: './mapping-gf-inc-code.component.html',
  styleUrls: ['./mapping-gf-inc-code.component.css']
})
export class MappingGfIncCodeComponent implements OnInit {

  private listData: any[] = [];
  private actionsModal: string;

  constructor(
    private messageBarService: MessageBarService
  ) { }

  ngOnInit() {
    this.listData = [
      ['4102020103', 'รายได้ภาษีสุรา', '203010', 'ภาษีสุรา'],
      ['4102020109', 'รายได้ภาษีรถยนต์', '250010', 'ภาษีีแบตเตอรี่'],
      ['4201020103', 'ร/ดค่าปรับคดี', '804203', 'ค่าปรับเปรียบเทียบคดีสุรา'],
      ['4201010199', 'ร/ดอนุญาตอื่น', '410020', 'ค่าอนุญาติจำหน่ายสุรา ป2.'],
      ['4202030105', 'ร/ดค่าของเบ็ดเตล็ด', '830099', 'รายได้เบ็ดเตล็ดอื่นๆ']
    ];
  }

  ngAfterViewInit() {
    $("#selectTax").hide();
    $("#selectCatagory").hide();
    //$("#selectTax").dropdown();
    //$('#selectCatagory').dropdown();
  }

  onEditClick() {
    this.actionsModal = "แก้ไข";
    $("#selectTax").show();
    $("#selectCatagory").show();
    $('#modalMappingIncCode').modal('show');
  }

  onAddClick() {
    this.actionsModal = "เพิ่ม";
    $("#selectTax").show();
    $("#selectCatagory").show();
    $('#modalMappingIncCode').modal('show');
  }

  closeModal() {
    $("#selectTax").hide();
    $("#selectCatagory").hide();
    $('#modalMappingIncCode').modal('hide');
  }
}
