package org.example.buyticket.app.model.search.criteria.range;

import org.example.buyticket.app.infra.util.Checks;

/**
 * Pagination parameters for data retrieval operations
 * @author Gulyamov Rustam
 *
 */
public class RangeCriteria {
    /**
     * Page index (0-base)
     */
    private final int page;

    /**
     * Number of element per page
     */
    private final int elementNumber;

    public RangeCriteria(final int page,final int elementNumber) {
        Checks.checkParameter(page >= 0, "incorrect number of page: " + page);
        Checks.checkParameter(elementNumber > 0, "incorrect number of element: " + elementNumber);

        this.page = page;
        this.elementNumber = elementNumber;
    }

    public int getPage() {
        return page;
    }

    public int getElementNumber() {
        return elementNumber;
    }
}
