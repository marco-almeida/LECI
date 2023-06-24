import { writable } from 'svelte/store';

export const admin_tab = writable('admin');
export let posts = writable([]);