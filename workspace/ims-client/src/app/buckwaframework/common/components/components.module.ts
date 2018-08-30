import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ConditionComponent } from './condition/condition.component';
import { DropdownComponent } from './dropdown/dropdown.component';
import { MessageBarComponent } from './message-bar/message-bar.component';
import { PaginationComponent } from './pagination/pagination.component';
import { ModalComponent } from './modal/modal.component';

@NgModule({
    imports: [
        CommonModule,
        FormsModule
    ],
    declarations: [
        ConditionComponent,
        DropdownComponent,
        MessageBarComponent,
        ModalComponent,
        PaginationComponent
    ],
    exports: [
        // Components
        ConditionComponent,
        DropdownComponent,
        MessageBarComponent,
        ModalComponent,
        PaginationComponent,
        // Modules
        CommonModule,
        FormsModule
    ]
})
export class ComponentsModule { }