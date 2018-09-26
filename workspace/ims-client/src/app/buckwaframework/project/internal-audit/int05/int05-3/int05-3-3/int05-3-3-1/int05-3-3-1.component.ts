import { AssetBalance, AssetMaintenance } from '../../../../../../common/models';
import { Component, OnInit } from "@angular/core";
import { AjaxService } from "../../../../../../common/services/ajax.service";
import { MessageBarService } from "../../../../../../common/services/message-bar.service";
import { Router, ActivatedRoute } from "@angular/router";
import { Utils, TextDateTH, formatter } from '../../../../../../common/helper';

declare var $: any;
@Component({
  selector: 'int05-3-3-1',
  templateUrl: './int05-3-3-1.component.html',
  styleUrls: ['./int05-3-3-1.component.css']
})
export class Int05331Component implements OnInit {
  assetBalance: AssetBalance;
  assetMaintenance: AssetMaintenance;
  day: any;
  month: any;
  year: any;
  constructor(private router: Router,
    private ajax: AjaxService,
    private messageBarService: MessageBarService,
    private route: ActivatedRoute) {

    this.assetBalance = new AssetBalance();
    this.assetMaintenance = new AssetMaintenance();

  }

  ngOnInit() {
    $(".ui.dropdown").dropdown();
    $(".ui.dropdown.ai").css("width", "100%");

    $('#documentDate').calendar({
      type: 'date',
      text: TextDateTH,
      formatter: formatter('วดป'),
      onChange: (date) => {


      }
    });

    $('#dateOfManufacture').calendar({
      type: 'date',
      text: TextDateTH,
      formatter: formatter('วดป'),
      onChange: (date) => {


      }
    });


    $('#maintenanceDate').calendar({
      type: 'date',
      text: TextDateTH,
      formatter: formatter('วดป'),
      onChange: (date) => {


      }
    });
  }


  addAction() {
    console.log(this.assetBalance);
    console.log(this.assetMaintenance);
    if (Utils.isNull(this.assetBalance.governmentSector)) {
      this.messageBarService.errorModal("กรุณากรอก \"ส่วนราชการ\"", 'แจ้งเตือน');
      return;
    }
    if (Utils.isNull(this.assetBalance.institute)) {
      this.messageBarService.errorModal("กรุณากรอก \"หน่วยงาน\"", 'แจ้งเตือน');
      return;
    }
    var url = "ia/int0533/addAssetBalance";
    this.ajax.post(url, { assetBalance: this.assetBalance, assetMaintenance: this.assetMaintenance }, res => {
      var message = res.json();
      console.log(message.messageType);
      if (message.messageType == 'E') {
        this.messageBarService.errorModal(message.messageTh, 'แจ้งเตือน');
      } else {
        this.messageBarService.successModal(message.messageTh, 'บันทึกข้อมูลสำเร็จ');
        this.router.navigate(["/int05/3/3"], {

        });
      }

    });
  }

  cancel() {
    this.router.navigate(['int05/3/3'], {

    });
  }



}
