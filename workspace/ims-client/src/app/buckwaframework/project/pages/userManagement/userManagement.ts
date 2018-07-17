import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";

import { AjaxService } from "../../../common/services/ajax.service";
import { MessageBarService } from "../../../common/services/message-bar.service";

declare var jQuery: any;
declare var $: any;

@Component({
  selector: "page-userManagement",
  templateUrl: "userManagement.html"
})
export class UserManagementPage implements OnInit {
  userManagementDt: any;
  checkboxes;
  Array;
  private $form: any;

  constructor(
    private router: Router,
    private ajaxService: AjaxService,
    private messageBarService: MessageBarService
  ) {}

  ngOnInit(): void {
    this.$form = $("#userManagementForm");
  }

  ngAfterViewInit() {
    this.initDatatable();
  }

  delete(): void {
    var deletes = [];
    this.checkboxes = $(".ui.checkbox.tableDt");
    for (var i = 0; i <= this.checkboxes.length; i++) {
      if (this.checkboxes.checkbox("is checked")[i]) {
        deletes.push(this.checkboxes.find("[type=checkbox]")[i].value);
      }
    }

    const url = "preferences/userManagement/" + deletes;

    this.messageBarService.comfirm(res => {
      if (!res) return false;
      const deleteURL = `preferences/userManagement/${deletes.join(",")}`;
      this.ajaxService.delete(
        deleteURL,
        (success: Response) => {
          let body: any = success.json();
          this.messageBarService.success("ลบข้อมูลสำเร็จ.");
          this.search();
        },
        (error: Response) => {
          let body: any = error.json();
          this.messageBarService.error(body.error);
        }
      );
    }, "ยืนยันการลบ.");
  }

  initDatatable(): void {
    const URL = AjaxService.CONTEXT_PATH + "preferences/userManagement/search";
    this.userManagementDt = $("#userManagementDt").DataTable({
      lengthChange: false,
      searching: false,
      ordering: false,
      pageLength: 10,
      processing: true,
      serverSide: true,
      paging: true,
      pagingType: "full_numbers",
      ajax: {
        type: "GET",
        url: URL,
        data: function(params) {
          params["username"] = $("#username").val();
          params["enabled"] = $("#enabled").checkbox("is checked") ? "Y" : "";
          params["accountNonExpired"] = $("#accountNonExpired").checkbox(
            "is checked"
          )
            ? "Y"
            : "";
          params["credentialsNonExpired"] = $(
            "#credentialsNonExpired"
          ).checkbox("is checked")
            ? "Y"
            : "";
          params["accountNonLocked"] = $("#accountNonLocked").checkbox(
            "is checked"
          )
            ? "Y"
            : "";
          return params;
        }
      },
      columns: [
        {
          data: "userId",
          render: function(data, type, full, meta) {
            return (
              '<div class="ui checkbox tableDt"><input name="checkUserId" value="' +
              data +
              '" type="checkbox"><label></label></div>'
            );
          }
        },
        { data: "username" },
        { data: "sector" },
        { data: "enabled" },
        { data: "accountNonExpired" },
        { data: "credentialsNonExpired" },
        { data: "accountNonLocked" },
        {
          data: "userId",
          render: function() {
            return '<button type="button" class="ui mini button edit"><i class="pencil icon"></i> Edit</button>';
          }
        }
      ],
      columnDefs: [
        { targets: [0, 2, 3, 4, 5, 6], className: "center aligned" }
      ],
      rowCallback: (row, data, index) => {
        $("td > .edit", row).bind("click", () => {
          this.router.navigate(["/edit-userManagement", data.userId]);
        });
      }
    });
  }

  resetSearch() {
    this.$form.form("set values", {
      username: "",
      enabled: false,
      accountNonExpired: false,
      credentialsNonExpired: false,
      accountNonLocked: false
    });
    this.search();
  }

  search() {
    this.userManagementDt.ajax.reload();
  }
}
