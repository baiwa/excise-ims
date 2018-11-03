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
  loading: boolean = false;
  private selectZone: any[];
  private selectArea: any[];
  private SECTOR_VALUE: string = 'SECTOR_VALUE';
  sector: any[] = [];
  area: any[] = [];
  uploadlist: any[] = [];

  breadcrumb: BreadCrumb[] = [
    { label: 'ตรวจสอบภายใน', route: '#' },
    { label: 'การตรวจสอบรายได้', route: '#' },
    { label: 'ตรวจสอบงบสรุปยอดเงินค่าภาษีกับงบทดลอง', route: '#' },
  ];

  isSearch:boolean = false;

  constructor(
    private authService: AuthService,
    private route: ActivatedRoute,
    private router: Router,
    private ajax: AjaxService,
    private messageBarService: MessageBarService
  ) {

  }

  ngOnInit() {
    this.authService.reRenderVersionProgram('INT-01410');
  }

  ngAfterViewInit() {


  }

   onUpload = (event: any) => {
    event.preventDefault();

    this.loading = true;
    this.isSearch = true;

    const form = $("#upload-form")[0];
    let formBody = new FormData(form);

    let url = "ia/int014/uploadFileExcel";
    this.ajax.upload(
      url,
      formBody,
      res => {
        let resbody = res.json();
        console.log();
        this.loading = false;

        if (resbody.status == "0") {
          this.uploadlist = resbody.data.lines;
        } else {
          this.messageBarService.error("เกิดข้อผิดผลาดไม่สามารถอัพโหลดไฟล์ได้.");
        }

      },
      error => {
        this.loading = false;
      }
    );
  };


  gotoChecking(){
    this.router.navigate(['/int01/4/2',{ uploadid : '1' }]);
  }

  onclear(){
    this.uploadlist = [];
  }


}

