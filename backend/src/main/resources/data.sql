INSERT INTO MARKET (id, code, name) VALUES (1, 'LS', 'Lang & Schwarz');

INSERT INTO ASSET (id, description, isin, name) VALUES (1, '', 'IE00B52VJ196', 'iShares MSCI Europe SRI UCITS ETF (Acc)');
INSERT INTO ASSET (id, description, isin, name) VALUES (2, '', 'ES0144580Y14', 'Iberdrola');

INSERT INTO ASSET_EXCHANGE (price, shares, asset_id, market_id, id, exchange_type, timestamp) VALUES ('24.45', '2.0', 1, 1, 1, 1, '2026-09-26 12:00:45.025+00');
INSERT INTO ASSET_EXCHANGE (price, shares, asset_id, market_id, id, exchange_type, timestamp) VALUES ('20.92', '5.0', 2, 1, 2, 1, '2026-09-26 12:00:45.025+00');
INSERT INTO ASSET_EXCHANGE (price, shares, asset_id, market_id, id, exchange_type, timestamp)VALUES ('22.52', '1.0', 2, 1, 3, 2, '2026-09-26 12:00:45.025+00');