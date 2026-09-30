def deliver_password_reset_code(
    *,
    identifier: str,
    code: str,
) -> None:
    """Deliver a password reset code through the active development channel."""

    print(
        f"Password reset code for {_mask_identifier(identifier)}: {code}",
        flush=True,
    )


def _mask_identifier(identifier: str) -> str:
    normalized = identifier.strip()

    if "@" in normalized:
        local_part, domain = normalized.split("@", 1)

        if len(local_part) <= 2:
            masked_local = "*" * len(local_part)
        else:
            masked_local = (
                local_part[0]
                + "*" * (len(local_part) - 2)
                + local_part[-1]
            )

        return f"{masked_local}@{domain}"

    if len(normalized) <= 4:
        return "*" * len(normalized)

    return "*" * (len(normalized) - 4) + normalized[-4:]
