import { Component, OnInit, OnDestroy } from "@angular/core";
import { Router, ActivatedRoute } from "@angular/router";
import { AjaxService } from "../../../../../common/services/ajax.service";
import { MessageBarService } from "../../../../../common/services/message-bar.service";
import { BaseModel, ManageReq, BreadCrumb } from 'models/index';
import { AuthService } from "services/auth.service";

declare var $: any;
@Component({
  selector: 'app-int01-4-1',
  templateUrl: './int01-4-1.component.html',
  styleUrls: ['./int01-4-1.component.css']
})
export class Int0141Component implements OnInit {

  private selectZone: any[];
  private selectArea: any[];
  private SECTOR_VALUE: string = 'SECTOR_VALUE';
  sector: any[] = [];
  area: any[] = [];

  fileExel: File[];
  constructor(
    private router: Router,
    
private authService: AuthService,
    private route: ActivatedRoute,
    private ajax: AjaxService,
    private messageBarService: MessageBarService
  ) {

    this.fileExel = new Array<File>(); // initial file array
  }

  ngOnInit() {
    this.authService.reRenderVersionProgram('INT-01410');

  }

  ngAfterViewInit() {
    //$("#selectZone").dropdown();
    //$("#selectArea").dropdown();
  }

  sectorCombobox = (lovIdMaster) => {

    const URL = "combobox/controller/getDropByTypeAndParentId";
    this.ajax.post(URL, { type: this.SECTOR_VALUE, lovIdMaster: lovIdMaster }, res => {
      this.sector = res.json();
      console.log(this.sector);
    });

  }

  areaCombobox = (lovIdMaster) => {

    const URL = "combobox/controller/getDropByTypeAndParentId";
    this.ajax.post(URL, { type: this.SECTOR_VALUE, lovIdMaster: lovIdMaster }, res => {
      this.area = res.json();
      //console.log(this.sector);
    });

  }


  onUpload = (event: any) => {
    event.preventDefault();


    const form = $("#upload-form")[0];
    let formBody = new FormData(form);

    let url = "ia/int014/uploadFileExcel";
    this.ajax.upload(
      url,
      formBody,
      res => {
        //console.log(res.json());



      }
    );
  };

  onChangeUpload = (event: any) => {
    if (event.target.files && event.target.files.length > 0) {
      let reader = new FileReader();

      reader.onload = (e: any) => {
        const f = {
          name: event.target.files[0].name,
          type: event.target.files[0].type,
          value: e.target.result
        };
        this.fileExel = [f];
      };
      reader.readAsDataURL(event.target.files[0]);
    }
  };

}
class File {
  [x: string]: any;
  name: string;
  type: string;
  value: any;
}
