import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ConditionComponent } from './condition/condition.component';
import { DropdownComponent } from './dropdown/dropdown.component';
import { MessageBarComponent } from './message-bar/message-bar.component';
import { PaginationComponent } from './pagination/pagination.component';
import { ModalComponent } from './modal/modal.component';
import { AccordionComponent } from './accordion/accordion.component';
import { BreadcrumbComponent } from './breadcrumb/breadcrumb.component';

@NgModule({
    imports: [
        CommonModule,
        FormsModule
    ],
    declarations: [
        AccordionComponent,
        ConditionComponent,
        DropdownComponent,
        MessageBarComponent,
        ModalComponent,
        PaginationComponent,
        BreadcrumbComponent
    ],
    exports: [
        // Components
        AccordionComponent,
        ConditionComponent,
        DropdownComponent,
        MessageBarComponent,
        ModalComponent,
        BreadcrumbComponent,
        PaginationComponent,
        // Modules
        CommonModule,
        FormsModule
    ]
})
export class ComponentsModule { }