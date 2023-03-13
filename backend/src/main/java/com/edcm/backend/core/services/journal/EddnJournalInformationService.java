package com.edcm.backend.core.services.journal;

import com.edcm.backend.infrastructure.eddn.schemas.journal.EddnJournalDockedPayload;

public interface EddnJournalInformationService {

    void saveDockedJournalData(EddnJournalDockedPayload payload);
}
