package com.technical.credit.obligationfacade.facade;

import com.technical.credit.obligationfacade.data.ObligationData;

import java.util.List;

public interface ObligationFacade {
    /**
     * The saving obligation in the system.
     * If obligation with a same id has been already saved - then to do update the exist instance.
     * Localized fields are saved and populated according to session localization.
     *
     * @param obligation instance of obligation for saving
     * @return the saved obligation
     */
    ObligationData save(ObligationData obligation);

    /**
     * The searching of obligation by id.
     * Localized fields are saved and populated according to session localization.
     *
     * @param id id of require obligation
     * @return the found obligation.
     */
    ObligationData search(long id);

    /**
     * The pageable searching of obligation data by required text for sessions user.
     *
     * @param freeText  query text
     * @param sortField field for sorting
     * @param desc      desc/asc
     * @param page      page number
     * @param limit     limit count of result
     * @return the list of a search result.
     */
    List<ObligationData> search(String freeText, String sortField, boolean desc, int page, int limit);

    /**
     * Delete of obligation by id.
     *
     * @param id - the id of delete obligation
     */
    void delete(long id);

    /**
     * Method add an obligation to the category.
     *
     * @param obligationID - id of obligation
     * @param categoryID   id of category
     * @throws com.technical.credit.core.exception.ModelNotFoundException - if category or obligation is not found by ID
     */
    void assignToCategory(long obligationID, long categoryID);
}
