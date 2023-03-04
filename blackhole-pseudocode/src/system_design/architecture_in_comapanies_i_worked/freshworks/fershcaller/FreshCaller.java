/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package system_design.architecture_in_comapanies_i_worked.freshworks.fershcaller;

/**
 *
 * @author venka
 */
public class FreshCaller {
    
}

/*
Unified authentication integration with freshid has to be integrated from every product within the Freshworks ecosystem,

Implemented Freshid - authentication platform integration for Freshcaller and migrated from Freshid V1 to V2 org model. 
Coined the idea - chained migration for migrating product accounts to V2 without manual efforts on tracking integrated accounts.

Migration of integrated accounts is a hectic task as separating integrated accounts and standalone accounts is a manual effort.
so I was able to automate the whole migration process with the chained migration approach.



unit tests were taking longer and developers had to wait a long time after they pushed their code to GitHub and
only merge after the unit tests were completed successfully which was taking more than hours.

Parallelized Unit test that runs on CD/CI pipelines(Jenkins), reduced
its time complexity by the number of CPUs, n(usually 16).

since tests were running in parallel, it took only a fraction 9 of the time that it initially took, 
so developers dot have to wait anymore.
*/








